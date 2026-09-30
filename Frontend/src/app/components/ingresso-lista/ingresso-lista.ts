import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { IngressoService } from '../../services/ingresso.service';
import { Ingresso } from '../../models/ingresso.model';

@Component({
  selector: 'app-ingresso-lista',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './ingresso-lista.html',
  styleUrl: './ingresso-lista.css',
})
export class IngressoListaComponent implements OnInit {
  private readonly ingressoService = inject(IngressoService);

  readonly ingressos = signal<Ingresso[]>([]);
  readonly carregando = signal<boolean>(true);
  readonly erro = signal<string>('');
  readonly mensagemSucesso = signal<string>('');

  ngOnInit(): void {
    this.carregarIngressos();
  }

  carregarIngressos(): void {
    this.carregando.set(true);
    this.erro.set('');

    this.ingressoService.listarTodos().subscribe({
      next: (dados) => {
        console.log('[INGRESSO LISTA] Ingressos recebidos:', dados);
        this.ingressos.set(dados);
        this.carregando.set(false);
      },
      error: (err) => {
        console.error('[INGRESSO LISTA] Erro ao carregar:', err);
        this.erro.set(err?.mensagem ?? 'Erro ao carregar ingressos.');
        this.carregando.set(false);
      },
    });
  }

  deletarIngresso(id: string, codigo: string): void {
    if (!confirm(`Deseja cancelar o ingresso ${codigo}?`)) return;

    this.ingressoService.deletar(id).subscribe({
      next: () => {
        this.mensagemSucesso.set(`Ingresso ${codigo} cancelado com sucesso.`);
        this.carregarIngressos();
        setTimeout(() => this.mensagemSucesso.set(''), 3000);
      },
      error: (err) => {
        this.erro.set(err?.mensagem ?? 'Erro ao cancelar o ingresso.');
      },
    });
  }
}