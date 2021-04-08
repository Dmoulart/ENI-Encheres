function mainBs() {
    let curve = new Curve(
        -windowWidth,
        -windowHeight/1.5,
        windowWidth,
        windowHeight/5,
        "black",
        1,
        0.01, [
            { x: windowWidth * 0.8, y: windowHeight * 0.25 },
            { x: windowWidth * 0.85, y: windowHeight * 0.23 },
        ],
        "unstable"
    );
    curve.specialMode = true;
    curve.granularityCoefficients["spatial"] = 1;
    curve.granularityCoefficients["width"] = 1;
    let bs = new BrushStroke(curve, windowHeight * 0.04);
    bs.deviation = 25;
    bs.widthDistribution("random", [1, 10]);
    bs.colorDistribution("gradient", ["#7989a7", "#7989a7"]);

    return bs;
}

function ContactBs() {
    let curve = new Curve(-windowWidth,
        windowHeight,
        windowWidth,
        windowHeight*0.35,
        "black",
        1,
        0.01, [
            { x: -windowWidth * 0.5, y: windowHeight * 0.2 },
            { x: -windowWidth * 0.05, y: windowHeight * -0.8 },
        ],
        "unstable"
    );
    curve.granularityCoefficients["spatial"] = 20;
    curve.granularityCoefficients["width"] = 1;

    let bs = new BrushStroke(curve, windowHeight * 0.12);
    bs.deviation = 50;
    bs.widthDistribution("random", [1, 1]);
    bs.colorDistribution("gradient", ["#97c9d9", "#97c9d9"]);

    return bs;
}
