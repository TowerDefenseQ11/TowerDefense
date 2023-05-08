import pygame
import random

# Define some colors
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)
RED = (255, 0, 0)

# Set the width and height of the screen
SCREEN_WIDTH = 700
SCREEN_HEIGHT = 500

# Set the number of enemies to spawn
NUM_ENEMIES = 10

# Set the player's starting resources
STARTING_RESOURCES = 100

# Set the bullet speed
BULLET_SPEED = 5

# Set the enemy speed
ENEMY_SPEED = 2

# Define the Tower class
class Tower(pygame.sprite.Sprite):
    def __init__(self):
        super().__init__()
        self.image = pygame.Surface([20, 20])
        self.image.fill(BLACK)
        self.rect = self.image.get_rect()
        self.range = 100
        self.shoot_timer = 0

    def update(self, enemy_group, bullet_group):
        self.shoot_timer -= 1
        if self.shoot_timer <= 0:
            for enemy in enemy_group:
                distance = ((enemy.rect.x - self.rect.x) ** 2 + (enemy.rect.y - self.rect.y) ** 2) ** 0.5
                if distance <= self.range:
                    bullet = Bullet(self.rect.center, enemy.rect.center)
                    bullet_group.add(bullet)
                    self.shoot_timer = 60
                    break

# Define the Enemy class
class Enemy(pygame.sprite.Sprite):
    def __init__(self):
        super().__init__()
        self.image = pygame.Surface([20, 20])
        self.image.fill(RED)
        self.rect = self.image.get_rect()
        self.rect.x = random.randint(0, SCREEN_WIDTH - self.rect.width)
        self.rect.y = random.randint(0, SCREEN_HEIGHT - self.rect.height)
        self.speed = ENEMY_SPEED

    def update(self):
        self.rect.y += self.speed

# Define the Bullet class
class Bullet(pygame.sprite.Sprite):
    def __init__(self, start_pos, end_pos):
        super().__init__()
        self.image = pygame.Surface([4, 4])
        self.image.fill(BLACK)
        self.rect = self.image.get_rect()
        self.rect.center = start_pos
        self.start_pos = start_pos
        self.end_pos = end_pos
        self.speed = BULLET_SPEED

    def update(self):
        dx = self.end_pos[0] - self.start_pos[0]
        dy = self.end_pos[1] - self.start_pos[1]
        distance = ((dx ** 2) + (dy ** 2)) ** 0.5
        if distance <= self.speed:
            self.kill()
        else:
            angle = math.atan2(dy, dx)
            x_speed = self.speed * math.cos(angle)
            y_speed = self.speed * math.sin(angle)
            self.rect.x += x_speed
            self.rect.y += y_speed

# Initialize Pygame
pygame.init()

# Set the size of the screen
screen = pygame.display.set_mode([SCREEN_WIDTH, SCREEN_HEIGHT])

# Set the title of the window
pygame.display.set_caption("Tower Defense")

# Create the sprite groups
all_sprites_group = pygame.sprite.Group()
tower_group = pygame.sprite.Group()
enemy_group = pygame.sprite.Group()
bullet_group = pygame.sprite.Group()

# Create the player's resources
resources = STARTING_RESOURCES

# Create the player's tower
tower = Tower()
tower.rect.x = SCREEN_WIDTH // 2
tower.rect.y =
