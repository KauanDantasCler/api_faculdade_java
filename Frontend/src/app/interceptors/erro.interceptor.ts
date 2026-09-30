import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';

export const erroInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      let mensagem = 'Ocorreu um erro ao processar a requisição.';

      if (error.status === 0) {
        mensagem = 'Não foi possível conectar ao servidor. Verifique a conexão com a API.';
      } else if (error.status === 404) {
        mensagem = 'Recurso não encontrado.';
      } else if (error.status === 500) {
        mensagem = 'Erro interno do servidor.';
      }

      return throwError(() => ({
        status: error.status,
        mensagem,
        original: error
      }));
    })
  );
};