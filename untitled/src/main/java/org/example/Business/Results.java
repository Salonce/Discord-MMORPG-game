package org.example.Business;

import java.util.Random;

public class Results {
    public Results(CombatStrength combatStrengthA, CombatStrength combatStrengthB) {
        this.combatStrengthA = combatStrengthA;
        this.combatStrengthB = combatStrengthB;
        this.totalPowerA = calculateRandomTotalPower(combatStrengthA);
        this.totalPowerB = calculateRandomTotalPower(combatStrengthB);

        this.setResults(totalPowerA, totalPowerB);
    }

    public Result getResultA() {
        return resultA;
    }

    public Result getResultB() {
        return resultB;
    }

    private CombatStrength combatStrengthA;
    private CombatStrength combatStrengthB;
    private int totalPowerA;
    private int totalPowerB;
    private Result resultA;
    private Result resultB;

    private int calculateRandomTotalPower(CombatStrength combatStrength) {
        int minTotalPower = combatStrength.getHealth() * combatStrength.getMinAttack() * (combatStrength.getDefense() + combatStrength.getSpeed());
        int maxTotalPower = combatStrength.getHealth() * combatStrength.getMaxAttack() * (combatStrength.getDefense() + combatStrength.getSpeed());
        int randomTotalPower = minTotalPower;
        if (maxTotalPower > minTotalPower)
            randomTotalPower += new Random().nextInt(maxTotalPower - minTotalPower);
        return randomTotalPower;
    }

    private void setResults(int totalPowerA, int totalPowerB) {
        if (totalPowerA > totalPowerB) {
            double newHealth = (double) combatStrengthA.getHealth() - ((((double) totalPowerB) / (double) totalPowerA) * (double) combatStrengthA.getHealth());
            if (newHealth < 1)
                newHealth = 1;
            resultA = new Result((int) Math.round(newHealth), true);
            resultB = new Result(0, false);
        } else if (totalPowerB > totalPowerA) {
            double newHealth = (double) combatStrengthB.getHealth() - ((((double) totalPowerA) / totalPowerB) * combatStrengthB.getHealth());
            if (newHealth < 1)
                newHealth = 1;
            resultB = new Result((int) Math.round(newHealth), true);
            resultA = new Result(0, false);
        } else if (totalPowerA == totalPowerB) {
            int OneOrTwo = new Random().nextInt(2);
            if (OneOrTwo == 0) {
                resultA = new Result(1, true);
                resultB = new Result(0, false);
            } else {
                resultA = new Result(0, false);
                resultB = new Result(1, true);
            }
        }
    }
}
