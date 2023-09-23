package com.metehan.springbootfundamentals.one_RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private long playerId;
    private String playerName;
    private double averageScore;

}
