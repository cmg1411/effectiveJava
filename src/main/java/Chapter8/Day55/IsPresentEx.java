package Chapter8.Day55;

import java.util.Optional;

public class IsPresentEx {

    public static void main(String[] args) {
        ProcessHandle process = ProcessHandle.current();
        Optional<ProcessHandle> parentProcess = process.parent();
        System.out.println("부모 PID : " + (parentProcess.isPresent() ? String.valueOf(parentProcess.get().pid()) : "N/A"));

        System.out.println("부모 PID : " + process.parent().map(h -> String.valueOf(h.pid())).orElse("N/A"));
    }
}
