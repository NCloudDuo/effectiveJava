package junghyeok.chapter6.item34;

public enum PayrollDay {
    MONDAY(PayType.WEEKDAY), TUESDAY(PayType.WEEKDAY), WEDNESDAY(PayType.WEEKDAY),
    THURSDAY(PayType.WEEKDAY),FRIDAY(PayType.WEEKDAY),SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);

    private final PayType payType;

    PayrollDay(final PayType payType) {
        this.payType = payType;
    }

    int pay(int minutesWorked, int payRate){
        return payType.pay(minutesWorked, payRate);
    }

    enum PayType{
        WEEKDAY{
            @Override
            int overtimePay(int mins, int payRate) {
                return 0;
            }
        },
        WEEKEND{
            @Override
            int overtimePay(int mins, int payRate) {
                return 0;
            }
        };

        abstract int overtimePay(int mins, int payRate);

        private static final int MINS_PER_SHIFT = 8 * 60;

        int pay(int minsWorked, int payRate){
            int basePay = minsWorked * payRate;
            return basePay + overtimePay(minsWorked, payRate);
        }
    }
}
