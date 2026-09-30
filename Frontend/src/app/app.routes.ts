import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: 'eventos', pathMatch: 'full' },
  {
    path: 'eventos',
    loadComponent: () =>
      import('./components/evento-lista/evento-lista').then(
        (m) => m.EventoListaComponent
      ),
  },
  {
    path: 'eventos/novo',
    loadComponent: () =>
      import('./components/evento-form/evento-form').then(
        (m) => m.EventoFormComponent
      ),
  },
  {
    path: 'eventos/editar/:id',
    loadComponent: () =>
      import('./components/evento-form/evento-form').then(
        (m) => m.EventoFormComponent
      ),
  },
  {
    path: 'ingressos',
    loadComponent: () =>
      import('./components/ingresso-lista/ingresso-lista').then(
        (m) => m.IngressoListaComponent
      ),
  },
  {
    path: 'ingressos/emitir/:eventoId',
    loadComponent: () =>
      import('./components/ingresso-form/ingresso-form').then(
        (m) => m.IngressoFormComponent
      ),
  },
];