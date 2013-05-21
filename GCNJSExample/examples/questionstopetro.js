/*
Questions to Petro:
1. Does page.tag('content').edit("#content"); only work if Aloha has been loaded properly?
-> Yes, with integration plugin

2. Why does var content = page.tag('content').part('text'); not work
-> old, broken version => fixed in latest

3. When do I have to use a callback to complete the chain
=> You need to use a callback whenever you need to work with a value

4. When do I have to call save and what does it do (tag vs. page)?
 -> code snippets suggest a save function on the tag object
 never use it on the tag => ugly, it will save the page
 use it on the page

*/