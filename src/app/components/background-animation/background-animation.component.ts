// import { Component, ElementRef, OnInit, ViewChild, HostListener, Inject, PLATFORM_ID, AfterViewInit } from '@angular/core';
// import { isPlatformBrowser } from '@angular/common';

// @Component({
//   selector: 'app-background-animation',
//   standalone: true,
//   templateUrl: './background-animation.component.html',
//   styleUrls: ['./background-animation.component.scss']
// })
// export class BackgroundAnimationComponent implements AfterViewInit {
//   @ViewChild('background') backgroundCanvas!: ElementRef<HTMLCanvasElement>;
//   @ViewChild('foreground1') foreground1Canvas!: ElementRef<HTMLCanvasElement>;
//   @ViewChild('foreground2') foreground2Canvas!: ElementRef<HTMLCanvasElement>;

//   private bctx!: CanvasRenderingContext2D;
//   private fctx1!: CanvasRenderingContext2D;
//   private fctx2!: CanvasRenderingContext2D;

//   private config = {
//     circle: {
//       amount: 18,
//       layer: 3,
//       color: [157, 97, 207],
//       alpha: 0.3
//     },
//     line: {
//       amount: 12,
//       layer: 3,
//       color: [255, 255, 255],
//       alpha: 0.3
//     },
//     speed: 0.5,
//     angle: 20
//   };

//   private circles: Array<{x: number, y: number, radius: number, speedX: number, speedY: number, color: string, alpha: number}> = [];

//   constructor(@Inject(PLATFORM_ID) private platformId: object) {}


//   ngAfterViewInit(): void {
//     if (isPlatformBrowser(this.platformId)) {
//       this.initCanvas();
//       this.initCircles();
//       this.startAnimation();
//     }
//   }

//   private initCanvas(): void {
//     this.bctx = this.backgroundCanvas.nativeElement.getContext('2d')!;
//     this.fctx1 = this.foreground1Canvas.nativeElement.getContext('2d')!;
//     this.fctx2 = this.foreground2Canvas.nativeElement.getContext('2d')!;
//     this.resizeCanvas();
//   }

//   @HostListener('window:resize', ['$event'])
//   onResize(event: Event): void {
//     this.resizeCanvas();
//   }

//   private resizeCanvas(): void {
//     const width = window.innerWidth;
//     const height = window.innerHeight;
//     [this.backgroundCanvas, this.foreground1Canvas, this.foreground2Canvas].forEach(canvasEl => {
//       const canvas = canvasEl.nativeElement;
//       canvas.width = width;
//       canvas.height = height;
//     });
//   }

//   private initCircles(): void {
//     for (let i = 0; i < this.config.circle.amount; i++) {
//       const radius = Math.random() * 20 + 5; // Random radius between 5 and 25
//       this.circles.push({
//         x: Math.random() * window.innerWidth,
//         y: Math.random() * window.innerHeight,
//         radius: radius,
//         speedX: (Math.random() - 0.5) * this.config.speed,
//         speedY: (Math.random() - 0.5) * this.config.speed,
//         color: `rgba(${this.config.circle.color.join(',')},${this.config.circle.alpha})`,
//         alpha: this.config.circle.alpha
//       });
//     }
//   }

//   private drawCircle(circle: {x: number, y: number, radius: number, color: string}): void {
//     this.fctx1.beginPath();
//     this.fctx1.arc(circle.x, circle.y, circle.radius, 0, Math.PI * 2, false);
//     this.fctx1.fillStyle = circle.color;
//     this.fctx1.fill();
//   }

//   private startAnimation(): void {
//     this.fctx1.clearRect(0, 0, this.foreground1Canvas.nativeElement.width, this.foreground1Canvas.nativeElement.height);
//     this.circles.forEach(circle => {
//       circle.x += circle.speedX;
//       circle.y += circle.speedY;

//       // Reverse direction if hitting the canvas bounds
//       if(circle.x + circle.radius > window.innerWidth || circle.x - circle.radius < 0) {
//         circle.speedX *= -1;
//       }
//       if(circle.y + circle.radius > window.innerHeight || circle.y - circle.radius < 0) {
//         circle.speedY *= -1;
//       }

//       this.drawCircle(circle);
//     });
//     requestAnimationFrame(this.startAnimation.bind(this));
//   }
// }
// background-animation.component.ts
import { Component} from '@angular/core';

@Component({
    selector: 'app-background-animation',
    standalone: true,
    templateUrl: './background-animation.component.html',
    styleUrls: ['./background-animation.component.scss']
  })
export class BackgroundAnimationComponent {
}
