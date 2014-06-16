object Basics_Worksheet { 
	case class GenRandInt(lb : Int, ub : Int){
		private val rnd = new scala.util.Random
		def next() : Int = { lb + rnd.nextInt(ub) }
	}
	
	case class Player(rating: Int){
		var playerRating: Int = rating
	}
	
	var randGenerator = new GenRandInt(100, 200)
	var startingNum: Int = 10                 
	var maxNum: Int = 100000000               
	var magnitude: Int = 10                   
	var current: Int = startingNum            

	def ArrayTest(current: Int){
		var playerArray: Array[Player] = new Array[Player](current)

		for(i <- 0 to current - 1){
			playerArray(i) = new Player(randGenerator.next())
		}
		
		var totalAbove: Int = 0
		
		var t0: Long = System.nanoTime()
		
		for(i <- 0 to current - 1){
			if(playerArray(i).playerRating > 150){
				totalAbove += 1
			}
		}
		
		var t1: Long = System.nanoTime()
		printf("Array Data: \n")
		printf("\tNumber of elements: " + current + "\n")
		printf("\tTime to complete: " + ((t1 - t0))/1000000000.0 + " nano seconds\n")	
	}
	
	def QueueTest(current: Int){
		var playerQueue = new scala.collection.mutable.Queue[Player]()
		
		//Array version
		for(i <- 0 to current - 1){
			playerQueue.enqueue(new Player(randGenerator.next()))
		}
		
		var totalAbove: Int = 0
		
		var t0: Long = System.nanoTime()
		
		for(i <- 0 to current - 1){
			if(playerQueue(i).playerRating > 150){
				totalAbove += 1
			}
		}
		
		var t1: Long = System.nanoTime()
		printf("Queue Data: \n")
		printf("\tNumber of elements: " + current + "\n")
		printf("\tTime to complete: " + ((t1 - t0))/1000000000.0 + " nano seconds\n")
	}
	
	def main(args: Array[String]){
		while(current < maxNum){
			printf("Current test: " + current)
			printf("Time started: " + System.nanoTime() + "\n")
			ArrayTest(current)
			printf("Time end: " + System.nanoTime() + "\n")
			printf("Time started: " + System.nanoTime() + "\n")
			QueueTest(current)
			printf("Time end: " + System.nanoTime() + "\n")
			printf("Current test end: \n\n")
			
			current *= magnitude
		}
	}
}
