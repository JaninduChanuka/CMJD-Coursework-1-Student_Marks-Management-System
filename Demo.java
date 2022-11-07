class Demo {
    int num = 50;

    void change(Demo c) {
        c.num = c.num + 100;
    }

    public static void main(String args[]) {
        Demo c = new Demo();

        System.out.println("Before " + c.num);
        c.change(c);
        System.out.println("After  " + c.num);
    }
}