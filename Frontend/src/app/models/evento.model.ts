export interface Evento {
  id?: string;
  nome: string;
  date: string;
  local: string;
  descricao?: string;
  capacidade?: number;
}