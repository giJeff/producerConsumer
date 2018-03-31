package producerConsumer;

import java.util.LinkedList;

public class MiniOS
{
	// Shared list size of 2
	LinkedList<Integer> sharedMemory = new LinkedList<>();
	int maxValue = 2;

	public void produce() throws InterruptedException
	{
		int count = 0;
		while (true)
		{
			synchronized (this)
			{
				// producer thread waits while list is full
				while (sharedMemory.size() == maxValue)
					wait();

				System.out.println("Producing process: " + count);

				sharedMemory.add(count++);

				notify();

				// slow down output to see what's going on
				Thread.sleep(1000);
			}
		}
	}

	public void consume() throws InterruptedException
	{
		while (true)
		{
			synchronized (this)
			{
				// consumer thread waits while list is empty
				while (sharedMemory.size() == 0)
					wait();

				// to retrieve the first job (FILO)
				int count = sharedMemory.removeFirst();

				System.out.println("Consuming process: " + count);

				notify();

				Thread.sleep(1000);
			}
		}
	}
}
