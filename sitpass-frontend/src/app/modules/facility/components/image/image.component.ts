import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-image',
  templateUrl: './image.component.html',
  styleUrls: ['./image.component.scss']
})
export class ImageComponent implements OnInit {
  images: { src: string, alt: string }[] = [
    { src: 'images/ter2.jpg', alt: 's1' },
    { src: 'images/ter2.jpg', alt: 's2' },
    { src: 'images/ter2.jpg', alt: 's3' },
    { src: 'images/ter2.jpg', alt: 's4' },
    { src: 'images/ter2.jpg', alt: 's5' },
  ];

  currentIndex: number = 0;

  ngOnInit(): void {
    this.updateVisibleImages();
  }

  shouldDisplayImage(index: number): boolean {
    return index >= this.currentIndex && index < this.currentIndex + 3;
  }

  prevSlide(): void {
    if (this.currentIndex > 0) {
      this.currentIndex--;
    }
  }

  nextSlide(): void {
    if (this.currentIndex < this.images.length - 3) {
      this.currentIndex++;
    }
  }

  private updateVisibleImages(): void {
    // Ova metoda je suvišna u trenutnoj logici
  }
}
