import { Component } from '@angular/core';
import { ApiService } from './../../api.service';
import { Pessoa } from './../../entities/Pessoa'; 
import * as moment from 'moment';
import { MatPaginator } from '@angular/material/paginator';
import { ViewChild } from '@angular/core';

@Component({
  selector: 'app-pessoa-lista',
  templateUrl: './pessoa-lista.component.html',
  styleUrls: ['./pessoa-lista.component.scss']
})
export class PessoaListaComponent {
  pessoasList: Pessoa[] = [];
  pessoasListTotal: Pessoa[] = [];
  expandedRowIndex: number | null = null;
  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;

  constructor(private apiService: ApiService){}


  ngOnInit(){
    this.getPessoasPaginated(0, 5);
    this.getPessoas();
  }

  onPageChange(event: any) {
    const pageIndex  = event.pageIndex;
    const pageSize = event.pageSize;

    this.getPessoasPaginated(pageIndex , pageSize);
  }

  getPessoas() {
    this.apiService.getPessoas().subscribe(
      (response: Pessoa[]) => {
        this.pessoasListTotal = response;
        this.pessoasListTotal.map((p) => p.dataNascimento = moment(p.dataNascimento).format('DD/MM/YYYY'));
        this.paginator.length = this.pessoasListTotal.length;
      },
      error => {
        console.error(error);
      }
    );
  }

  getPessoasPaginated(page: number, pageSize: number) {
    this.apiService.getPessoasPaginated(page, pageSize).subscribe(
      (response: any) => {
        this.pessoasList = response["content"];
        this.pessoasList.map((p) => p.dataNascimento = moment(p.dataNascimento).format('DD/MM/YYYY'));
      },
      error => {
        console.error(error);
      }
    );
  }

  deletePessoa(id: number ){
    console.log("deletando: ", id)
    this.apiService.deletePessoa(id).subscribe();
  }

  deleteContato(pessoaId: number, contatoId: number){
    console.log(pessoaId, contatoId)
  }

  createContato(){
    console.log("Creating Contato")
  }

  editContato(pessoaId: number, contatoId: number){
    console.log("Updating Contato ", pessoaId, contatoId)
  }

  editPessoa(pessoaId: number){
    console.log("Updating Pessoa ", pessoaId)
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
