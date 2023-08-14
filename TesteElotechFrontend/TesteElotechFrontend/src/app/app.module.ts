import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';

import {MatToolbarModule} from '@angular/material/toolbar';
import {MatTabsModule} from '@angular/material/tabs';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatListModule} from '@angular/material/list';
import {MatTableModule} from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';

import { PessoaListaComponent } from './components/pessoa-lista/pessoa-lista.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PessoaListaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatToolbarModule,
    MatTabsModule,
    BrowserAnimationsModule,
    MatListModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
