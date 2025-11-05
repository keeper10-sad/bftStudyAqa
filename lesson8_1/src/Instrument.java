public  class Instrument {

    public enum Strings {
        pianoStrings (230),
        guitarStrings(6);
        private final int count;

        Strings(int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }
    }

    public static class Guitar implements Playable {

        @Override
        public void play(int strings) {
            System.out.println("Звучание гитары обеспичивают " + strings+ " струн(-ы)");
        }
    }

    public static class Piano implements Playable {

        @Override
        public void play(int strings) {
            System.out.println("Звучание пианино обеспечивают " + strings + " струн(-ы)");
        }
    }

    public static void main(String[] args) {
        Guitar guitar = new Guitar();
        Piano piano = new Piano();

        int guitarStrings = Strings.guitarStrings.getCount();
        int pianoStrings = Strings.pianoStrings.getCount();

        guitar.play(guitarStrings);
        piano.play(pianoStrings);
    }
}
