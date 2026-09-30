import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { IngressoService } from '../../services/ingresso.service';
import { EventoService } from '../../services/evento.service';
import { Evento } from '../../models/evento.model';

@Component({
  selector: 'app-ingresso-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './ingresso-form.html',
  styleUrl: './ingresso-form.css',
})
export class IngressoFormComponent implements OnInit {
  private readonly fb = inject(FormBuilder);
  private readonly ingressoService = inject(IngressoService);
  private readonly eventoService = inject(EventoService);
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);

  readonly evento = signal<Evento | null>(null);
  readonly eventoId = signal<string>('');
  readonly carregando = signal<boolean>(false);
  readonly salvando = signal<boolean>(false);
  readonly erro = signal<string>('');

  form = this.fb.group({
    nomeParticipante: ['', [Validators.required, Validators.minLength(3)]],
    emailParticipante: ['', [Validators.required, Validators.email]],
    preco: [50, [Validators.required, Validators.min(0)]],
  });

  ngOnInit(): void {
    const evId = this.route.snapshot.paramMap.get('eventoId');
    if (evId) {
      this.eventoId.set(evId);
      this.carregarEvento(evId);
    }
  }

  carregarEvento(id: string): void {
    this.carregando.set(true);
    this.eventoService.buscarPorId(id).subscribe({
      next: (ev) => {
        this.evento.set(ev);
        this.carregando.set(false);
      },
      error: () => {
        this.carregando.set(false);
      },
    });
  }

  emitir(): void {
    if (this.form.invalid || !this.eventoId()) {
      this.form.markAllAsTouched();
      return;
    }

    this.salvando.set(true);
    this.erro.set('');

    const dados = this.form.value as any;

    this.ingressoService.emitir(this.eventoId(), dados).subscribe({
      next: (res) => {
        console.log('[INGRESSO FORM] Ingresso emitido com sucesso:', res);
        this.router.navigate(['/ingressos']);
      },
      error: (err) => {
        console.error('[INGRESSO FORM] Erro ao emitir:', err);
        this.erro.set(err?.mensagem ?? 'Erro ao emitir ingresso.');
        this.salvando.set(false);
      },
    });
  }

  get f() {
    return this.form.controls;
  }
}