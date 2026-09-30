import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ingresso } from '../models/ingresso.model';

@Injectable({
  providedIn: 'root'
})
export class IngressoService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/ingresso';

  listarTodos(): Observable<Ingresso[]> {
    return this.http.get<Ingresso[]>(this.apiUrl);
  }

  buscarPorId(id: string): Observable<Ingresso> {
    return this.http.get<Ingresso>(`${this.apiUrl}/${id}`);
  }

  emitir(eventoId: string, ingresso: Ingresso): Observable<Ingresso> {
    return this.http.post<Ingresso>(`${this.apiUrl}/evento/${eventoId}`, ingresso);
  }

  atualizar(id: string, ingresso: Ingresso): Observable<Ingresso> {
    return this.http.put<Ingresso>(`${this.apiUrl}/${id}`, ingresso);
  }

  deletar(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}