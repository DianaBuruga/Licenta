import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgFor } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIcon } from '@angular/material/icon';
import { ExperienceDto } from '../../services/models';

interface Experience {
  title: string;
  description: string;
  date: string;
  url: string,
  type: string,
  image: string,
}

@Component({
  selector: 'app-projects-carousel',
  standalone: true,
  imports: [
    MatCardModule,
    CommonModule,
    NgFor,
    MatIcon
],
  templateUrl: './projects-carousel.component.html',
  styleUrl: './projects-carousel.component.scss'
})
export class ProjectsCarouselComponent{
  @Input() experiences: ExperienceDto[] | undefined;
  ngOnInit(): void {
    console.log('Experiences', this.experiences);
  }
  // experiences: Experience[] = [
  //   {
  //     title: 'Proiect 1',
  //     description: 'Description of product 1',
  //     date: '18-04-2024',
  //     url:'https://github.com/',
  //     type: 'COMPETITON',
  //     image: 'path/to/image1.jpg'
  //   },
  //   {
  //     title: 'Product 1',
  //     description: 'Description of product 1',
  //     date: 'path/to/image1.jpg',
  //     url:'',
  //     type: 'COMPETITON',
  //     image: ''
  //   },
  // ];
  currentIndex = 0;

  showNext(): void {
    if(this.experiences === undefined) return;
    if (this.currentIndex < this.experiences.length - 1) {
      this.currentIndex++;
    } else {
      this.currentIndex = 0;
    }
  }

  showPrev(): void {
    if(this.experiences === undefined) return;
    if (this.currentIndex > 0) {
      this.currentIndex--;
    } else {
      this.currentIndex = this.experiences.length - 1;
    }
  }
  showExperience(index: number) {
    this.currentIndex = index;
}
onImageError(event: any) {
  event.target.style.display = 'none';
}
}
