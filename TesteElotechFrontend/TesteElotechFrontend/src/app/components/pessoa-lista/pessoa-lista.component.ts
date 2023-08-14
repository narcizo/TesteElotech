import { Component } from '@angular/core';
import { ApiService } from './../../api.service';
import { Pessoa } from './../../entities/Pessoa'; 
import * as moment from 'moment';
import { Contato } from 'src/app/entities/Contato';

@Component({
  selector: 'app-pessoa-lista',
  templateUrl: './pessoa-lista.component.html',
  styleUrls: ['./pessoa-lista.component.scss']
})
export class PessoaListaComponent {
  pessoasList: Pessoa[] = [];
  expandedRowIndex: number | null = null;

  constructor(private apiService: ApiService){}

  ngOnInit(){
    this.getPessoas();
  }

  getPessoas() {
    this.apiService.getPessoas().subscribe(
      (response: Pessoa[]) => {
        this.pessoasList = response;
        this.pessoasList.map((p) => p.dataNascimento = moment(p.dataNascimento).format('DD/MM/YYYY'));
        console.log(this.pessoasList);
      },
      error => {
        console.error(error);
      }
    );
  }

  // getContatos(id: number) {
  //   this.apiService.getContatos(id).subscribe(
  //     (response: Contato[]) => {
  //       this.pessoasList.map((p)=>{
  //         if(p.id == id){
  //           p.contatos = response;
  //           console.log(p.contatos);
  //         }
  //       })
  //       console.log(this.pessoasList);
  //     },
  //     error => {
  //       console.error(error);
  //     }
  //   );
  // }

  deletePessoa(id: number ){
    console.log("deletando: ", id)
  }

  deleteContato(pessoaId: number, contatoId: number){
    console.log(pessoaId, contatoId)
  }

  createContato(){
    console.log("Create contato")
  }

  updateContato(pessoaId: number, contatoId: number){
    console.log(pessoaId, contatoId)
  }

  toggleRow(pessoa: Pessoa) {
    if (this.isRowExpanded(pessoa)) {
      this.expandedRowIndex = null;
    } else {
      this.expandedRowIndex = this.pessoasList.indexOf(pessoa);
    }
  }


  isRowExpanded(pessoa: Pessoa): boolean {
    return this.expandedRowIndex === this.pessoasList.indexOf(pessoa);
  }
  
}
