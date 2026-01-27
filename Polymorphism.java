// Overloading
class Animal{
    public void bark(){
        System.out.println("Animal sound");
    }
}

class Dog extends Animal{
    @Override
    public void bark(){
        System.out.println("Dog sound");
    }
}

class Polymorphism {

    static int sum(int a, int b) {
        return a + b;
    }

    static double sum(double a, double b) {
        return a + b;
    }

    public static void main(String[] args) {
        System.out.println(sum(4, 5));        // gọi sum(int, int)
        System.out.println(sum(5.9, 10.6));   // gọi sum(double, double)

        Animal x=new Dog();
        x.bark();
    }
}

