package by.training.task15.command;

public interface Command {
	void setInput(double[] in);
	void execute();
	double getResult();
}
