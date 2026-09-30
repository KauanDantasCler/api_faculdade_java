import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { EventoService } from '../../services/evento.service';
import { Evento } from '../../models/evento.model';

@Component({
  selector: 'app-evento-lista',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './evento-lista.html',
  styleUrl: './evento-lista.css',
})
export class EventoListaComponent implements OnInit {
  private readonly eventoService = inject(EventoService);
  private readonly router = inject(Router);

  readonly eventos = signal<Evento[]>([]);
  readonly carregando = signal<boolean>(true);
  readonly erro = signal<string>('');
  readonly mensagemSucesso = signal<string>('');

  ngOnInit(): void {
    this.carregarEventos();
  }

  carregarEventos(): void {
    this.carregando.set(true);
    this.erro.set('');

    this.eventoService.listarTodos().subscribe({
      next: (dados) => {
        this.eventos.set(dados);
        this.carregando.set(false);
      },
      error: (err) => {
        this.erro.set(err?.mensagem ?? 'Erro ao carregar eventos do servidor.');
        this.carregando.set(false);
      },
    });
  }

  editarEvento(id: string): void {
    this.router.navigate(['/eventos/editar', id]);
  }

  deletarEvento(id: string, nome: string): void {
    if (!confirm(`Deseja realmente excluir o evento "${nome}"?`)) return;

    this.eventoService.deletar(id).subscribe({
      next: () => {
        this.mensagemSucesso.set(`Evento "${nome}" removido com sucesso.`);
        this.carregarEventos();
        setTimeout(() => this.mensagemSucesso.set(''), 3000);
      },
      error: (err) => {
        this.erro.set(err?.mensagem ?? 'Erro ao remover o evento.');
      },
    });
  }
}