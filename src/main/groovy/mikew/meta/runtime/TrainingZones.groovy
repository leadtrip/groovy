package mikew.meta.runtime

enum TrainingZone {
    ONE('Active recovery', (0..55)),
    TWO('Aerobic capacity', (56..75)),
    THREE('Tempo', (76..90)),
    FOUR('Sweet spot', (84..97)),
    FIVE('Threshold', (98..104)),
    SIX('VO2 Max', (105..120)),
    SEVEN('Neuromuscular power', (120..500))

    String desc
    Range<Integer> pcThreshold

    TrainingZone(d, r ) {
        desc = d
        pcThreshold = r
    }
}