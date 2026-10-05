package UPsay.decouverteAndroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.os.Handler;

public class MonView extends View {
    int compteur = 0;
    float xText, yText;
    float x1, y1, x2, y2;
    public MonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setXYText(600, 600);

        // Timer : rappelle onDraw toutes les 100 ms
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();                    // redessine la vue (rappelle onDraw)
                handler.postDelayed(this, 100);  // se relance dans 100 ms
            }
        };
        handler.postDelayed(runnable, 100);
    }


    public void setXYText(float x, float y) {
        xText = x;
        yText = y;
    }
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        /* définir la couleur de l'objet de dessin */
        p.setColor(Color.BLACK);
        /* définir son style en remplissage */
        p.setStyle(android.graphics.Paint.Style.FILL);
        /* dessiner un rectangle qui occupe la totalité du View */
        canvas.drawRect(0, 0, getWidth(), getHeight(), p);
        /* définir une autre couleur pour dessiner un texte */
        p.setColor(Color.GREEN);
        /* définir la taille du texte */
        p.setTextSize(100);
        /* définir le centre du texte comme étant son origine */
        p.setTextAlign(android.graphics.Paint.Align.CENTER);
        /* dessiner le texte au centre du View */
        String texte = "Bonjour MONDE";
        canvas.drawText(texte, xText, yText, p);
        /* charger l'image emoji.png depuis le dossier drawable */
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.formulaone);
        if (b != null) {
            canvas.drawBitmap(b, getWidth() / 2 - b.getWidth() / 2, getHeight() / 2 - b.getHeight() / 2, p);
        }
        /* afficher et faire avancer le compteur */
        p.setColor(Color.YELLOW);
        p.setTextSize(60);
        canvas.drawText("Compteur : " + compteur, getWidth() / 2, getHeight() - 100, p);
        compteur = compteur + 1;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float dx, dy;
        String direction;
        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                x1 = event.getX();
                y1 = event.getY();
                Log.i("pacman", "appuyé");
                break;

            case (MotionEvent.ACTION_UP): {
                x2 = event.getX();
                y2 = event.getY();
                dx = x2 - x1;
                dy = y2 - y1;
                // Utiliser dx et dy pour déterminer la direction
                if (Math.abs(dx) > Math.abs(dy)) {
                    if (dx > 0)
                        direction = "right";
                    else
                        direction = "left";
                } else {
                    if (dy > 0)
                        direction = "down";
                    else
                        direction = "up";
                }
                Log.i("pacman", "lâché " + direction);
                Log.i("pacman", "dx = " + dx + "; dy = " + dy);
                break;
            }
        }
        invalidate();
        return true;
    }
}
