
let boardCanvarea;

let {windowHeight, windowWidth} = setWindowDimensions();

let boardBrushStrokes = new Array(2);

let initBoardBrushStrokes = [
    mainBs,
    ContactBs,
];

let secondsPassed = 0;
let oldTimeStamp = 0;

initBoardCanvareas();

initBrushStrokes();

startRenderLoop();

window.addEventListener(
    "orientationchange",
    function () {
        permuteCanvasDimensions();
        reSizeCanvas();
        initBrushStrokes();
        boardCanvarea.filter
            = new Filter(0, boardCanvarea.htmlCanvas.height / 6, 0.8, 0.85);
    },
    false
);

window.addEventListener(
    "resize",
    function () {
        setWindowDimensions()
        windowHeight = innerHeight;
        windowWidth = innerWidth;
        reSizeCanvas();
        initBrushStrokes();
        boardCanvarea.filter
            = new Filter(0, boardCanvarea.htmlCanvas.height / 6, 0.8, 0.85);
    },
    false
);
boardCanvarea.filter
    = new Filter(0, boardCanvarea.htmlCanvas.height / 6, 0.8, 0.85);


function startRenderLoop() {
    window.requestAnimationFrame(renderLoop);
}

function renderLoop(timeStamp) {
    secondsPassed = (timeStamp - oldTimeStamp) / 1000;
    oldTimeStamp = timeStamp;

    boardBrushStrokes[0].draw(1, boardCanvarea);


    window.requestAnimationFrame(renderLoop);
}

function initBoardCanvareas() {
    boardCanvarea = new Canvarea(document.getElementById("canvas1"));
	reSizeCanvas();
}


function reSizeCanvas() {
	if(innerHeight < 1000 && (innerHeight > innerWidth *2)){
		windowWidth = windowHeight;
	}
    boardCanvarea.setDimensions(windowHeight * 2, windowWidth);
}

function permuteCanvasDimensions() {
    let canvasHeightTemp = windowHeight;
    windowHeight = windowWidth; //holy shit i know
    windowWidth = canvasHeightTemp;

	if(innerHeight < 800 && (innerHeight > innerWidth *2)){
		windowWidth = windowHeight;
	}
}

function initBrushStrokes() {
    resetBoardBrushStrokes();
}

function resetBoardBrushStrokes() {
    for (let i = 0; i < boardBrushStrokes.length; i++) {
        boardBrushStrokes[i] = initBoardBrushStrokes[i]();
    }
}

function setWindowDimensions() {
    let windowHeight = innerHeight;
    let windowWidth = innerWidth;
    return {windowHeight: windowHeight, windowWidth: windowWidth};
}