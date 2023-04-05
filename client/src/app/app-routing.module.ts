import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchReviewComponentComponent } from './components/search-review-component.component';
import { CommentComponent } from './components/comment.component';

const routes: Routes = [
  {
    path: '',
    component: SearchReviewComponentComponent,
  },
  { path: 'comment', component: CommentComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
