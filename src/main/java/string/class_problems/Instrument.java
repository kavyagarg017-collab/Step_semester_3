abstract class Instrument {

    public Instrument() {
    }

    public String play() {
        return "";
    }
}

class StringInstrument extends Instrument {

    public StringInstrument() {
        super();
    }

    @Override
    public String play() {
        super.play();
        return "Strumming the strings";
    }
}

class Violin extends StringInstrument {

    public Violin() {
        super();
    }

    @Override
    public String play() {
        super.play();
        return "Strumming the strings, with a bow drawn across four strings";
    }
}