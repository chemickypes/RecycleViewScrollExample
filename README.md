# RecycleViewScrollExample
Progetto personale per esercizio su la RecycleView e gli aspetti particolari dello scrollListener

This example shows how to hide/show a bar (not Toolbar or ActionBar) while recycleview is scrolling.

Two class are so important:

1. UPSrrollListener: listener of scrolling
2. MyRecycleViewAdapter: adapter. I put a fake Header to RecycleView (layout is called spessore.xml) to create a gap 
between Bar and RecycleView

## General Info
BlankFragment contains RecycleView. I'm sorry for its name. 
Navigation Drawer is useless, but I put it because I need environment like an App I'm working on.

UPScrollListener algorithm is based on work of mzgreen https://github.com/mzgreen/HideOnScrollExample

