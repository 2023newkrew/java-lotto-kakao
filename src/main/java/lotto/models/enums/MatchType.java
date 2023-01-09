package lotto.models.enums;

public enum MatchType {
    MATCH {
        @Override
        public boolean pass(boolean include) {
            return include;
        }
    },
    INCONSISTENCY {
        @Override
        public boolean pass(boolean include) {
            return !include;
        }
    },
    IRRELEVANT {
        @Override
        public boolean pass(boolean include) {
            return true;
        }
    };

    public abstract boolean pass(boolean include);
}
