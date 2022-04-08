package com.example.rocket_elevator_formation_api.controller;

import com.example.rocket_elevator_formation_api.model.Quote;
import com.example.rocket_elevator_formation_api.repository.DataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/calcul")
public class QuoteCalculationController {
    private final DataService repository;
    private final int STANDARD_PRICE = 7565;
    private final int PREMIUM_PRICE = 12345;
    private final int EXCELIUM_PRICE = 15400;

    private final double STANDARD_FEES = 0.10;
    private final double PREMIUM_FEES = 0.13;
    private final double EXCELIUM_FEES = 0.16;

    public QuoteCalculationController(DataService repository) {
        this.repository = repository;
    }

    // GET http://localhost:8080/calcul/residential/{appartValue}/{stageValue}
    @GetMapping("residential/{appartValue}/{stageValue}")
    public int numberElevatorResidential(@PathVariable int appartValue,@PathVariable int stageValue) {
        double moy = Math.ceil((float) appartValue/ (float)stageValue);
        int numElevator = (int) Math.ceil(moy/6);

        if (numElevator == 0) {
            numElevator++;
        }
        if (stageValue > 20 == true) {
            double column = Math.ceil((float) stageValue / 20);
            numElevator *= column;
        }
        return numElevator;
    }

    // GET http://localhost:8080/calcul/commercial/{numElevator}
    @GetMapping("commercial/{numElevator}")
    public int numberElevatorCommercial(@PathVariable int numElevator) {
        return numElevator;
    }

    // GET http://localhost:8080/calcul/corporate/{numStage}/{numBasement}/{maxOccup}
    @GetMapping("corporate/{numStage}/{numBasement}/{maxOccup}")
    public int numberElevatorCorporate(@PathVariable int numStage,@PathVariable int numBasement,@PathVariable int maxOccup) {
        int totalFloors = numStage + numBasement;
        int totalOccup = maxOccup*totalFloors;
        int numElevator = Math.round(totalOccup/1000);
        if (numElevator == 0) {
            numElevator++;
        }
        int elevatorColumns = (int) Math.ceil((float) totalFloors/20);
        if (elevatorColumns == 0) {
            elevatorColumns++;
        }
        int averageElCol = (int) Math.ceil((float) numElevator/(float) elevatorColumns);
        return averageElCol*elevatorColumns;
    }

    // GET http://localhost:8080/calcul/hybrid/{numStage}/{numBasement}/{maxOccup}
    @GetMapping("hybrid/{numStage}/{numBasement}/{maxOccup}")
    public int numberElevatorHybrid(@PathVariable int numStage,@PathVariable int numBasement,@PathVariable int maxOccup) {
        int totalFloors = numStage + numBasement;
        int totalOccup = maxOccup*totalFloors;
        int numElevator = Math.round(totalOccup/1000);
        if (numElevator == 0) {
            numElevator++;
        }
        int elevatorColumns = (int) Math.ceil((float) totalFloors/20);
        if (elevatorColumns == 0) {
            elevatorColumns++;
        }
        int averageElCol = (int) Math.ceil((float) numElevator/(float) elevatorColumns);
        return averageElCol*elevatorColumns;
    }

    // GET http://localhost:8080/calcul/totalElevatorPrice/{priceType}/{amountElevator}
    @GetMapping("totalElevatorPrice/{priceType}/{amountElevator}")
    // get the total price of elevator when you choose the option on the radio button
    public double getTotalElevatorPrice(@PathVariable int priceType,@PathVariable int amountElevator) {
        var total = 0;
        if (priceType == STANDARD_PRICE) {
            return amountElevator * STANDARD_PRICE;
        }
        else if (priceType == PREMIUM_PRICE) {
            return amountElevator * PREMIUM_PRICE;
        }else if (priceType == EXCELIUM_PRICE) {
            return amountElevator * EXCELIUM_PRICE;
        }
        return total;
    }

    // GET http://localhost:8080/calcul/standardFees/{fees}/{total}
    @GetMapping("standardFees/{fees}/{total}")
    public double getFees(@PathVariable int fees,@PathVariable double total) {
        if (fees == STANDARD_PRICE){
            return total * STANDARD_FEES;
        }else if (fees == PREMIUM_PRICE){
            return total * PREMIUM_FEES;
        }else if (fees == EXCELIUM_PRICE){
            return total * EXCELIUM_FEES;
        }
        return 0;
    }

    // GET http://localhost:8080/calcul/finalPrice/{totalElevatorPrice}/{installationFees}
    @GetMapping("finalPrice/{totalElevatorPrice}/{installationFees}")
    // get fees on total elevator price when you choose Excelium
    public double getFinalPrice(@PathVariable double totalElevatorPrice,@PathVariable double installationFees) {
        return totalElevatorPrice + installationFees;
    }

}
