import { Component } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  template: `
    <header class="navbar">
      <div class="navbar-container">
        <a routerLink="/eventos" class="navbar-brand">
          <span>Plataforma de Eventos</span>
        </a>
        
        <nav class="navbar-nav">
          <a routerLink="/eventos" routerLinkActive="active" [routerLinkActiveOptions]="{exact: true}">Eventos</a>
          <a routerLink="/ingressos" routerLinkActive="active">Ingressos Emitidos</a>
          <span class="nav-divider"></span>
          <a routerLink="/eventos/novo" class="btn-cta">+ Novo Evento</a>
        </nav>
      </div>
    </header>

    <main class="main-content">
      <router-outlet />
    </main>

    <footer class="footer">
      <p>Plataforma de Eventos &copy; 2026 &bull; Gerencie seus eventos com excelência.</p>
    </footer>
  `,
  styles: [`
    .navbar {
      background-color: #0a0d14;
      border-bottom: 1px solid #1e293b;
      position: sticky;
      top: 0;
      z-index: 100;
    }
    .navbar-container {
      max-width: 1080px;
      margin: 0 auto;
      height: 64px;
      padding: 0 24px;
      display: flex;
      align-items: center;
      justify-content: space-between;
    }
    .navbar-brand {
      color: #ffffff;
      text-decoration: none;
      font-weight: 700;
      font-size: 1.05rem;
      letter-spacing: -0.2px;
    }
    .navbar-nav {
      display: flex;
      align-items: center;
      gap: 20px;
    }
    .navbar-nav a:not(.btn-cta) {
      color: #94a3b8;
      text-decoration: none;
      font-size: 0.88rem;
      font-weight: 500;
      transition: color 0.15s ease;
    }
    .navbar-nav a:not(.btn-cta):hover,
    .navbar-nav a:not(.btn-cta).active {
      color: #ffffff;
    }
    .nav-divider {
      width: 1px;
      height: 18px;
      background-color: #334155;
    }
    .btn-cta {
      background-color: #f43f5e;
      color: #ffffff !important;
      text-decoration: none;
      padding: 8px 18px;
      border-radius: 6px;
      font-size: 0.85rem;
      font-weight: 600;
      transition: background-color 0.15s ease;
    }
    .btn-cta:hover {
      background-color: #e11d48;
    }
    .main-content {
      flex: 1;
      max-width: 1080px;
      width: 100%;
      margin: 0 auto;
      padding: 36px 24px;
    }
    .footer {
      border-top: 1px solid #e2e8f0;
      padding: 24px 0;
      text-align: center;
      background: #ffffff;
      margin-top: auto;
    }
    .footer p {
      font-size: 0.8rem;
      color: #94a3b8;
    }
  `],
})
export class App {}