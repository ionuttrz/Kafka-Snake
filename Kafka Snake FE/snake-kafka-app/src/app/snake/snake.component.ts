import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-snake',
  standalone: true,
  templateUrl: './snake.component.html',
  styleUrls: ['./snake.component.css'],
  imports: [FormsModule, CommonModule]
})
export class SnakeComponent {
  @ViewChild('gameCanvas', { static: true }) canvas!: ElementRef<HTMLCanvasElement>;

  private ctx!: CanvasRenderingContext2D;
  private snake: { x: number, y: number }[] = [];
  private food = { x: 0, y: 0 };
  private dx = 10;
  private dy = 0;
  private intervalId: any;
  public score: number | null = null;
  public username = ''; 

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.ctx = this.canvas.nativeElement.getContext('2d')!;
    document.addEventListener('keydown', this.changeDirection.bind(this));
  }

  startGame() {
    if (!this.username.trim()) {
      alert("Please enter a username first!");
      return;
    }

    this.resetGame();
    this.sendKafkaMessage(`User ${this.username} started playing`);
    this.intervalId = setInterval(this.gameLoop.bind(this), 100);
  }

  resetGame() {
    this.snake = [{ x: 50, y: 50 }];
    this.dx = 10;
    this.dy = 0;
    this.createFood();
    this.score = 0;
  }

  createFood() {
    this.food = {
      x: Math.floor(Math.random() * 39) * 10,
      y: Math.floor(Math.random() * 39) * 10
    };
  }

  changeDirection(event: KeyboardEvent) {
    const keyPressed = event.key;
    if (keyPressed === 'ArrowUp' && this.dy === 0) {
      this.dx = 0;
      this.dy = -10;
    } else if (keyPressed === 'ArrowDown' && this.dy === 0) {
      this.dx = 0;
      this.dy = 10;
    } else if (keyPressed === 'ArrowLeft' && this.dx === 0) {
      this.dx = -10;
      this.dy = 0;
    } else if (keyPressed === 'ArrowRight' && this.dx === 0) {
      this.dx = 10;
      this.dy = 0;
    }
  }

  gameLoop() {
    const head = { x: this.snake[0].x + this.dx, y: this.snake[0].y + this.dy };
    this.snake.unshift(head);

    // Game over conditions
    if (head.x < 0 || head.x >= 400 || head.y < 0 || head.y >= 400 || this.isCollision(head)) {
      clearInterval(this.intervalId);
      this.sendKafkaMessage(`User ${this.username} ended the game with score ${this.score}`);
      return;
    }

    if (head.x === this.food.x && head.y === this.food.y) {
      this.score! += 1;
      this.createFood();
      // NEW: Send message when user increases score
      this.sendKafkaMessage(`User ${this.username} increased his score to ${this.score}`);
    } else {
      this.snake.pop();
    }

    this.draw();
  }

  isCollision(head: { x: number, y: number }) {
    return this.snake.slice(1).some(segment => segment.x === head.x && segment.y === head.y);
  }

  draw() {
    this.ctx.fillStyle = 'black';
    this.ctx.fillRect(0, 0, 400, 400);

    this.ctx.fillStyle = 'lime';
    this.snake.forEach(segment => {
      this.ctx.fillRect(segment.x, segment.y, 10, 10);
    });

    this.ctx.fillStyle = 'red';
    this.ctx.fillRect(this.food.x, this.food.y, 10, 10);
  }

  sendKafkaMessage(message: string) {
    this.http.post('http://localhost:8081/api/kafka/publish', { message }, { responseType: 'text' }).subscribe({
      next: () => console.log('Message sent:', message),
      error: (err) => console.error('Failed to send Kafka message', err)
    });
  }  
}
