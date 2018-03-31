package producerConsumer;

public class ProducerConsumer
{
	public static void main(String[] args) throws InterruptedException
	{
		MiniOS miniOS = new MiniOS();

		Thread producerThread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					miniOS.produce();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		});

		// Create consumer thread
		Thread consumerThread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					miniOS.consume();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		});

		// Start both threads
		producerThread.start();
		consumerThread.start();

		// producing must finish before consuming
		producerThread.join();
		consumerThread.join();
	}

}