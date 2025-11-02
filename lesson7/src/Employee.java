class Employee {
    private static String name;

    public Employee(String name) {
        this.name = name;
    }

    public void work() {
        System.out.println(name + "начинает работать");
    }


    static class Manager extends Employee {
        public Manager(String name) {
            super(name);
        }

        @Override
        public void work() {
            System.out.println("Менеджер " + name + " неспеша начинает работу");
        }
    }

        static class Developer extends Employee {
            public Developer(String name) {
                super(name);
            }

            public void work() {
                System.out.println("Разраб " + name + " начинает хардкодить");
            }
        }

            public static void main(String[] args) {
                Developer developer = new Developer("Игорь");
                Manager manager = new Manager("Юра");

                manager.work();
                developer.work();
            }
        }
