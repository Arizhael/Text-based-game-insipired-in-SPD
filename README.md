# Text-based game (inspired by SDP) - libGDX migration

This is a full migration of my original Swing project to libGDX while keeping the main gameplay logic:

- Character / Enemy / Entity logic preserved
- Exploration flow preserved
- Shop effects preserved
- Boss encounter and victory / defeat preserved
- Assets reused from the original project

## Project layout

- `core/`: shared game logic and UI
- `lwjgl3/`: desktop launcher
- `android/`: Android launcher
- `assets/`: images and enemy sprites from SPD

## How to run desktop

Having Gradle installed:

```bash
gradle lwjgl3:run
```

Or import the project as a Gradle project in IntelliJ / Android Studio.

## Notes

- This migration replaces Swing with libGDX Scene2D UI.
- Asset paths were normalized to `assets/Images` and `assets/Enemies`.
