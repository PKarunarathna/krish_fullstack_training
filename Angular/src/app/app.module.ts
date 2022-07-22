import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule} from '@angular/forms'

import { AppComponent } from './app.component';
import { EmployeesComponent } from './employees/employees.component';
import { ProgressBarComponent } from './shared/progress-bar/progress-bar.component';

//import { LkrFormatterPipe } from './lkr-formatter.pipe';

@NgModule({
  declarations: [
    AppComponent,
    EmployeesComponent,
    ProgressBarComponent,
    
    //LkrFormatterPipe,
    
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
