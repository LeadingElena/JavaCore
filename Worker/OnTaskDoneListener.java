package JavaCore.Worker;

@FunctionalInterface
public interface OnTaskDoneListener {
    void onDone (String result);
}
