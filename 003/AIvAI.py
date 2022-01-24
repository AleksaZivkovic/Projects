import numpy as np
import random
import pygame
import sys
import math
import pygame_menu

BLUE = (0,0,255)
BLACK = (0,0,0)
RED = (255,0,0)
YELLOW = (255,255,0)

ROW_COUNT = 6
COLUMN_COUNT = 7

PLAYER = 0
AI = 1

SESSION = 0

EMPTY = 0
PLAYER_PIECE = 1
AI_PIECE = 2

WINDOW_LENGTH = 4

DEPTH = 3
AI_PLAYER = None

image = pygame.image.load('setting.png')

def create_board():
	board = np.zeros((ROW_COUNT,COLUMN_COUNT))
	return board

def drop_piece(board, row, col, piece):
	board[row][col] = piece

def is_valid_location(board, col):
 	if col > 6:
 	 	return False

 	return board[ROW_COUNT-1][col] == 0

def get_next_open_row(board, col):
	for r in range(ROW_COUNT):
		if board[r][col] == 0:
			return r

def print_board(board):
	print(np.flip(board, 0))

def winning_move(board, piece):
	# Check horizontal locations for win
	for c in range(COLUMN_COUNT-3):
		for r in range(ROW_COUNT):
			if board[r][c] == piece and board[r][c+1] == piece and board[r][c+2] == piece and board[r][c+3] == piece:
				return True

	# Check vertical locations for win
	for c in range(COLUMN_COUNT):
		for r in range(ROW_COUNT-3):
			if board[r][c] == piece and board[r+1][c] == piece and board[r+2][c] == piece and board[r+3][c] == piece:
				return True

	# Check positively sloped diaganols
	for c in range(COLUMN_COUNT-3):
		for r in range(ROW_COUNT-3):
			if board[r][c] == piece and board[r+1][c+1] == piece and board[r+2][c+2] == piece and board[r+3][c+3] == piece:
				return True

	# Check negatively sloped diaganols
	for c in range(COLUMN_COUNT-3):
		for r in range(3, ROW_COUNT):
			if board[r][c] == piece and board[r-1][c+1] == piece and board[r-2][c+2] == piece and board[r-3][c+3] == piece:
				return True

def evaluate_window(window, piece):
	score = 0
	opp_piece = PLAYER_PIECE
	if piece == PLAYER_PIECE:
		opp_piece = AI_PIECE

	if window.count(piece) == 4: # 4 U REDU
		score += 100
	elif window.count(piece) == 3 and window.count(EMPTY) == 1: # 3 TE BOJE I JEDNO PRAZNO POLJE, TO JE DOBRO JER AKO STAVIMO NA TO POLJE....
		score += 5
	elif window.count(piece) == 2 and window.count(EMPTY) == 2:
		score += 2

	if window.count(opp_piece) == 3 and window.count(EMPTY) == 1:
		score -= 4

	return score

def score_position(board, piece):
	score = 0

	## Score center column
	center_array = [int(i) for i in list(board[:, COLUMN_COUNT//2])]
	center_count = center_array.count(piece)
	score += center_count * 3

	## Score Horizontal
	for r in range(ROW_COUNT):
		row_array = [int(i) for i in list(board[r,:])]
		for c in range(COLUMN_COUNT-3):
			window = row_array[c:c+WINDOW_LENGTH]
			score += evaluate_window(window, piece)

	## Score Vertical
	for c in range(COLUMN_COUNT):
		col_array = [int(i) for i in list(board[:,c])]
		for r in range(ROW_COUNT-3):
			window = col_array[r:r+WINDOW_LENGTH]
			score += evaluate_window(window, piece)

	## Score posiive sloped diagonal
	for r in range(ROW_COUNT-3):
		for c in range(COLUMN_COUNT-3):
			window = [board[r+i][c+i] for i in range(WINDOW_LENGTH)]
			score += evaluate_window(window, piece)

	for r in range(ROW_COUNT-3):
		for c in range(COLUMN_COUNT-3):
			window = [board[r+3-i][c+i] for i in range(WINDOW_LENGTH)]
			score += evaluate_window(window, piece)

	return score

def is_terminal_node(board):
	return winning_move(board, PLAYER_PIECE) or winning_move(board, AI_PIECE) or len(get_valid_locations(board)) == 0


def classic_minimax(board, depth, maximizingPlayer):
	valid_locations = get_valid_locations(board)
	is_terminal = is_terminal_node(board)
	if depth == 0 or is_terminal:
		if is_terminal:
			if winning_move(board, AI_PIECE):
				return (None, 100000000000000)
			elif winning_move(board, PLAYER_PIECE):
				return (None, -10000000000000)
			else: # Game is over, no more valid moves
				return (None, 0)
		else: # Depth is zero
			return (None, score_position(board, AI_PIECE))
	if maximizingPlayer:
		value = -math.inf
		column = random.choice(valid_locations)
		for col in valid_locations:
			row = get_next_open_row(board, col)
			b_copy = board.copy()
			drop_piece(b_copy, row, col, AI_PIECE)
			new_score = classic_minimax(b_copy, depth-1, False)[1]
			if new_score > value:
				value = new_score
				column = col
		return column, value

	else: # Minimizing player
		value = math.inf
		column = random.choice(valid_locations)
		for col in valid_locations:
			row = get_next_open_row(board, col)
			b_copy = board.copy()
			drop_piece(b_copy, row, col, PLAYER_PIECE)
			new_score = classic_minimax(b_copy, depth-1, True)[1]
			if new_score < value:
				value = new_score
				column = col
		return column, value


def minimax(board, depth, alpha, beta, maximizingPlayer):
	valid_locations = get_valid_locations(board)
	is_terminal = is_terminal_node(board)
	if depth == 0 or is_terminal:
		if is_terminal:
			if winning_move(board, AI_PIECE):
				return (None, 100000000000000)
			elif winning_move(board, PLAYER_PIECE):
				return (None, -10000000000000)
			else: # Game is over, no more valid moves
				return (None, 0)
		else: # Depth is zero
			return (None, score_position(board, AI_PIECE))
	if maximizingPlayer:
		value = -math.inf
		column = random.choice(valid_locations)
		for col in valid_locations:
			row = get_next_open_row(board, col)
			b_copy = board.copy()
			drop_piece(b_copy, row, col, AI_PIECE)
			new_score = minimax(b_copy, depth-1, alpha, beta, False)[1]
			if new_score > value:
				value = new_score
				column = col
			alpha = max(alpha, value)
			if alpha >= beta:
				break
		return column, value

	else: # Minimizing player
		value = math.inf
		column = random.choice(valid_locations)
		for col in valid_locations:
			row = get_next_open_row(board, col)
			b_copy = board.copy()
			drop_piece(b_copy, row, col, PLAYER_PIECE)
			new_score = minimax(b_copy, depth-1, alpha, beta, True)[1]
			if new_score < value:
				value = new_score
				column = col
			beta = min(beta, value)
			if alpha >= beta:
				break
		return column, value

def get_valid_locations(board):
	valid_locations = []
	for col in range(COLUMN_COUNT):
		if is_valid_location(board, col):
			valid_locations.append(col)
	return valid_locations

def pick_best_move(board, piece):

	valid_locations = get_valid_locations(board)
	best_score = -10000
	best_col = random.choice(valid_locations)
	for col in valid_locations:
		row = get_next_open_row(board, col)
		temp_board = board.copy()
		drop_piece(temp_board, row, col, piece)
		score = score_position(temp_board, piece)
		if score > best_score:
			best_score = score
			best_col = col

	return best_col

def draw_board(board):
	for c in range(COLUMN_COUNT):
		for r in range(ROW_COUNT):
			pygame.draw.rect(screen, BLUE, (c*SQUARESIZE, r*SQUARESIZE+SQUARESIZE, SQUARESIZE, SQUARESIZE))
			pygame.draw.circle(screen, BLACK, (int(c*SQUARESIZE+SQUARESIZE/2), int(r*SQUARESIZE+SQUARESIZE+SQUARESIZE/2)), RADIUS)
	
	for c in range(COLUMN_COUNT):
		for r in range(ROW_COUNT):		
			if board[r][c] == PLAYER_PIECE:
				pygame.draw.circle(screen, RED, (int(c*SQUARESIZE+SQUARESIZE/2), height-int(r*SQUARESIZE+SQUARESIZE/2)), RADIUS)
			elif board[r][c] == AI_PIECE: 
				pygame.draw.circle(screen, YELLOW, (int(c*SQUARESIZE+SQUARESIZE/2), height-int(r*SQUARESIZE+SQUARESIZE/2)), RADIUS)
    
    
	# pygame.draw.rect(screen, RED, (width + 25 ,height - 50, 50, 50))
  
	pygame.display.update()

def random_Player(board):
	arr = get_valid_locations(board)
	if len(arr) == 0:
		 return -1
	else:
		 return (random.choice(arr)) 




#####################################################################################
## MAIN

board = create_board()
#print_board(board)
game_over = False

pygame.init()

SQUARESIZE = 100

width = COLUMN_COUNT * SQUARESIZE
height = (ROW_COUNT+1) * SQUARESIZE

size = (width+100, height+100)

RADIUS = int(SQUARESIZE/2 - 5)

screen = pygame.display.set_mode(size)

def select_algo(_, difficulty):
	
	global DEPTH
	global AI_PLAYER
 
	if difficulty == 1:
		AI_PLAYER = random_Player
			
  
	if difficulty == 2:
		def f(board):
    			return classic_minimax(board, DEPTH, True)[0]

		AI_PLAYER = f

	if difficulty == 3:
		def f(board):
    			return minimax(board, DEPTH,-math.inf,math.inf, True)[0]

		AI_PLAYER = f

def start_the_game():
    global board
    global game_over
    global MOVES_CNT
    global AI_CNT
    global P_CNT
    global GAME_CNT
    global AI1_WINS
    global AI2_WINS
    global AI3_WINS
    global SESSION

    game_over = False
    MOVES_CNT = 0
    AI_CNT = 0
    P_CNT = 0
    
    board = create_board()
    main_loop()

select_algo(None, 1)

menu = pygame_menu.Menu('Welcome', 400, 300,
                       theme=pygame_menu.themes.THEME_BLUE)


def change_depth(value):
    global DEPTH
    try:
        DEPTH = int(value)
    except:
        pass


menu.add.text_input('Depth :', default='3', onchange=change_depth)
menu.add.selector('AI Alogo:', [('Random', 1), ('Classic minimax', 2), ('Alpha minimax', 3)], onchange=select_algo)
menu.add.button('New game', start_the_game)

def settinButtonClicked():
    global menu
    global screen
    menu.mainloop(screen)

pygame.display.update()

myfont = pygame.font.SysFont("monospace", 20)

GAME_CNT  = 1															# UKUPAN BROJ RUNDI
MOVES_CNT = 0   														# UKUPAN BROJ poteza
AI_CNT    = 0															# BROJ POTEZA AI
P_CNT     = 0															# MOJ BROJ POTEZA
AI1_WINS  = 0															# BROJ POBJEDA AI1
AI2_WINS  = 0															# BROJ POBJEDA AI2
AI3_WINS  = 0 															# BROJ POBJERA AI3
																# postavljamo ou labelu


turn = random.randint(PLAYER, AI)

def main_loop():
		global game_over
		global turn
		global SESSION
		global GAME_CNT
		global MOVES_CNT
		global AI_CNT
		global P_CNT
		global AI1_WINS
		global AI2_WINS
		global AI3_WINS
		global board
		global DEPTH
		global AI_PLAYER
  
		global image
  
		#screen.blit(image, (width + 25 ,height - 50, 50, 50))
 
		draw_board(board)
		MOVES_CNT_font = pygame.font.SysFont("monospace", 20)					#font za  labelu MOVES COUNTER:
		MOVES_CNT_label = MOVES_CNT_font.render("MOVES COUNTER: "+str(MOVES_CNT)+" AI MOVES: "+str(AI_CNT)+" P_CNT: "+str(P_CNT), 1, RED)	
		screen.blit(MOVES_CNT_label, (10,height+10))


		pygame.display.update()

		while not game_over:
			for event in pygame.event.get():
				if event.type == pygame.QUIT:
					pygame.quit()
					sys.exit()

			#screen.blit(image, (width + 25 ,height - 50, 50, 50))

			if SESSION == 0:
				select_algo('random vs minimax', turn + 1)
			elif SESSION == 1:
				select_algo('minimax vs alpha minimax', turn + 2)
			elif turn != AI:
				select_algo('random', 1)
			else:
				select_algo('alpha minimax', 3)

			if turn != AI:
				col = AI_PLAYER(board)

				if is_valid_location(board, col):
					row = get_next_open_row(board, col)
					drop_piece(board, row, col, PLAYER_PIECE)

					if winning_move(board, PLAYER_PIECE):
						if SESSION == 1:
							AI2_WINS += 1
						else:
							AI1_WINS += 1

						pygame.draw.rect(screen, BLACK, (0, 0, width*2, 200))

						if SESSION == 0:
							label = myfont.render("Random vs MiniMax (" + str(GAME_CNT) + ")", 1, YELLOW)
						elif SESSION == 1:
							label = myfont.render("MiniMax vs Alpha-Beta-MiniMax (" + str(GAME_CNT) + ")", 1, YELLOW)
						else:
							label = myfont.render("Random vs MiniMax (" + str(GAME_CNT) + ")", 1, YELLOW)
						screen.blit(label, (300,40))

						label = myfont.render("Algorithm 1 wins : " + str(AI1_WINS), 1, RED)
						screen.blit(label, (40,10))
						label = myfont.render("Algorithm 2 wins : " + str(AI2_WINS), 1, YELLOW)
						screen.blit(label, (40,40))
						label = myfont.render("Algorithm 3 wins : " + str(AI3_WINS), 1, BLUE)
						screen.blit(label, (40, 70))
						game_over = True

					draw_board(board)

					turn += 1
					turn = turn % 2

					MOVES_CNT += 1
					P_CNT += 1

					pygame.draw.rect(screen, BLACK, (0,height, width+100, SQUARESIZE))
					MOVES_CNT_label = MOVES_CNT_font.render("MOVES COUNTER: "+str(MOVES_CNT)+" AI 1 MOVES : "+str(AI_CNT)+" AI 2 MOVES : "+str(P_CNT), 1, RED)
					screen.blit(MOVES_CNT_label, (10,height+10))

					pygame.display.update()
					#pygame.time.wait(50)

			# # Ask for Player 2 Input
			if turn == AI and not game_over:

				#col = random.randint(0, COLUMN_COUNT-1)
				#col = pick_best_move(board, AI_PIECE)
				#test = random_Player(board)
				# print(test)
				col = AI_PLAYER(board)

				if is_valid_location(board, col):
					#pygame.time.wait(500)
					row = get_next_open_row(board, col)
					drop_piece(board, row, col, AI_PIECE)

					if winning_move(board, AI_PIECE):
						if SESSION == 0:
							AI2_WINS += 1
						else:
							AI3_WINS += 1

						pygame.draw.rect(screen, BLACK, (0, 0, width*2, 200))

						if SESSION == 0:
							label = myfont.render("Random vs MiniMax (" + str(GAME_CNT) + ")", 1, YELLOW)
						elif SESSION == 1:
							label = myfont.render("MiniMax vs Alpha-Beta-MiniMax (" + str(GAME_CNT) + ")", 1, YELLOW)
						else:
							label = myfont.render("Random vs MiniMax (" + str(GAME_CNT) + ")", 1, YELLOW)
						screen.blit(label, (300,40))

						label = myfont.render("Algorithm 1 wins : " + str(AI1_WINS), 1, RED)
						screen.blit(label, (40,10))
						label = myfont.render("Algorithm 2 wins : " + str(AI2_WINS), 1, YELLOW)
						screen.blit(label, (40,40))
						label = myfont.render("Algorithm 3 wins : " + str(AI3_WINS), 1 ,BLUE)
						screen.blit(label, (40, 70))
						game_over = True

					#print_board(board)
					draw_board(board)

					turn += 1
					turn = turn % 2

					MOVES_CNT+=1
					AI_CNT+=1

					pygame.draw.rect(screen, BLACK, (0,height, width+100, SQUARESIZE))
					MOVES_CNT_label = MOVES_CNT_font.render("MOVES COUNTER: "+str(MOVES_CNT)+" AI 1 MOVES: "+str(AI_CNT)+" AI 2 MOVES : "+str(P_CNT), 1, RED)
					screen.blit(MOVES_CNT_label, (10,height+10))

					pygame.display.update()
					#pygame.time.wait(50)

			if game_over:
				board = create_board()

				if GAME_CNT == 100:
					SESSION += 1
					GAME_CNT = 0
					if SESSION == 3:
						pygame.time.wait(5000)
						pygame.quit()
						sys.exit()
				GAME_CNT += 1
				P_CNT = 0
				AI_CNT = 0
				MOVES_CNT = 0
				game_over = False

menu.add.button('Exit', main_loop)
main_loop()
