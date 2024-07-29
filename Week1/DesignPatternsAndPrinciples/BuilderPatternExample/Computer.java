package Week1.DesignPatternsAndPrinciples.BuilderPatternExample;

public class Computer {
    private String cpu;
    private int ram;
    private int storage;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
    }

    public static class Builder {
        private String cpu;
        private int ram;
        private int storage;

        public Builder setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder setRam(int ram) {
            this.ram = ram;
            return this;
        }

        public Builder setStorage(int storage) {
            this.storage = storage;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }

    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram=" + ram +
                ", storage=" + storage +
                '}';
    }

    public static void main(String[] args) {
        Computer computer = new Computer.Builder()
                .setCpu("RYZEN 5")
                .setRam(16)
                .setStorage(512)
                .build();

        System.out.println(computer);
    }
}
