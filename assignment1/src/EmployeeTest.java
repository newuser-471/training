import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

class Employee{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String name;

    private int id;

    private int age;
}

public class EmployeeTest {

    //answer to ques1
    public List<Employee> removeDuplicates(List<Employee> list){
        Set<Integer> idSet = new HashSet<>();
        return list.stream().filter(e-> idSet.add(e.getId())).collect(Collectors.toList());
    }

    //answer to ques2
    public List<Employee> removeOldEmployee(List<Employee> list){
        return list.stream().filter(e->e.getAge()>40).collect(Collectors.toList());
    }

    //answer to ques3
    public List<List<Employee>> partitionByAge(List<Employee> list){
        return (List<List<Employee>>) list.stream().collect(Collectors.partitioningBy((e)->e.getAge()>=40)).values();
    }

    //answer to ques4
    public static Integer getSum(){
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i<5;i++){
            list.add(new Random().nextInt(10));
        }
        System.out.println(list);
        return list.parallelStream().mapToInt(i->i).sum();
    }

    public static void main(String[] args) {
        Integer res = 0;
        for(int i = 0;i<3;i++){
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->getSum());
            try {
                res+= future.get();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        System.out.println(res);
    }
}

