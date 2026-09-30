export interface Ingresso {
  id?: string;
  codigoIngresso?: string;
  nomeParticipante: string;
  emailParticipante: string;
  preco: number;
  eventoId?: string;
}