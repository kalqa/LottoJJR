package pl.lotto.domain.numberreceiver;

public class HashGeneratorTestImpl implements HashGenerable {


    private final String hash;

    HashGeneratorTestImpl(String hash) {
        this.hash = hash;
    }

    public HashGeneratorTestImpl() {
        hash = "123";
    }

    @Override
    public String getHash() {
        return hash;
    }
}
