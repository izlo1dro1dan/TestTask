package testTask.Utils;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reader {
    public String reader(){
        String data = "";
        try{
            String graphFile = "Graph.txt";
            Path path = Paths.get(getClass().getClassLoader()
                    .getResource(graphFile).toURI());

            Stream<String> lines = Files.lines(path);
            data = lines.collect(Collectors.joining("\n"));
            lines.close();
        }catch (URISyntaxException e){
            System.out.println(e.getMessage());
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return data;
    }
}
