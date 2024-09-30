package app.work;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CommandExecutor {
    List<ICommand> commands = new ArrayList<>();
    Deque<Runnable> undos = new ArrayDeque<>();

    public void add(ICommand cmd) {
        this.commands.add(cmd);
    }

    public boolean execute() {
        for (var cmd : commands) {
            try {
                var undo = cmd.execute();
                undos.push(undo);
            } catch(Exception e) {
                for (var undo : undos) {
                    undo.run();
                }
            }
        }
        return true;
    }

    interface ICommand {
        Runnable execute() throws Exception;
    }
}
