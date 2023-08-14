import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Observable } from 'rxjs';
import { Pessoa } from './entities/Pessoa';
import { Contato } from './entities/Contato';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  getPessoas(): Observable<Pessoa[]>{
    return this.http.get<Pessoa[]>(`${environment.apiUrl}/pessoa`);
  }

  getContatos(id: number): Observable<Contato[]>{
    return this.http.get<Contato[]>(`${environment.apiUrl}/pessoa/${id}/contatos`);
  }

  getPessoasPaginated(page: number, pageSize: number): Observable<Pessoa[]>{
    return this.http.get<Pessoa[]>(`${environment.apiUrl}/pessoa/paginated?page=${page}&pageSize=${pageSize}`);
  }

  deletePessoa(id: number){
    return this.http.delete<void>(`${environment.apiUrl}/pessoa`);
  }
}
