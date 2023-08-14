import {Contato} from './Contato'

export interface Pessoa {
    id: number;
    nome: string;
    cpf: string;
    dataNascimento: string;
    contatos: Contato[];
  }