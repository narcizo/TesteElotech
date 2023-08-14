import { Component } from '@angular/core';
import { ApiService } from './../../api.service';
import { Pessoa } from './../../entities/Pessoa'; 

@Component({
  selector: 'app-pessoa-lista',
  templateUrl: './pessoa-lista.component.html',
  styleUrls: ['./pessoa-lista.component.scss']
})
export class PessoaListaComponent {
  pessoasList: Pessoa[] = [];

  constructor(private apiService: ApiService){}

  ngOnInit(){
    this.getPessoas();
  }

  getPessoas() {
    this.apiService.getPessoas().subscribe(
      (response: Pessoa[]) => { // Use the Pessoa interface as the type
        this.pessoasList = response;
        console.log(this.pessoasList);
      },
      error => {
        console.error(error);
      }
    );
  }

}
