import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { EventoService } from '../../services/evento.service';

@Component({
  selector: 'app-evento-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './evento-form.html',
  styleUrl: './evento-form.css',
})
export class EventoFormComponent implements OnInit {
  private readonly fb = inject(FormBuilder);
  private readonly eventoService = inject(EventoService);
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);

  readonly modoEdicao = signal<boolean>(false);
  readonly eventoId = signal<string | null>(null);
  readonly carregando = signal<boolean>(false);
  readonly salvando = signal<boolean>(false);
  readonly erro = signal<string>('');

  form = this.fb.group({
    nome: ['', [Validators.required, Validators.minLength(3)]],
    date: ['', Validators.required],
    local: ['', Validators.required],
    descricao: [''],
  });

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.modoEdicao.set(true);
      this.eventoId.set(id);
      this.carregarEvento(id);
    }
  }

  carregarEvento(id: string): void {
    this.carregando.set(true);
    this.eventoService.buscarPorId(id).subscribe({
      next: (evento) => {
        this.form.patchValue({
          nome: evento.nome,
          date: evento.date ?? '',
          local: evento.local,
          descricao: evento.descricao ?? '',
        });
        this.carregando.set(false);
      },
      error: (err) => {
        this.erro.set(err?.mensagem ?? 'Erro ao carregar os dados do evento.');
        this.carregando.set(false);
      },
    });
  }

  salvar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.salvando.set(true);
    this.erro.set('');
    const dados = this.form.value as any;

    const idAtual = this.eventoId();
    const operacao = this.modoEdicao() && idAtual
      ? this.eventoService.atualizar(idAtual, dados)
      : this.eventoService.criar(dados);

    operacao.subscribe({
      next: () => {
        this.router.navigate(['/eventos']);
      },
      error: (err) => {
        this.erro.set(err?.mensagem ?? 'Erro ao salvar o evento.');
        this.salvando.set(false);
      },
    });
  }

  get f() {
    return this.form.controls;
  }
}