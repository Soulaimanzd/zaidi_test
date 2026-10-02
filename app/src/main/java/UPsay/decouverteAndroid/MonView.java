package UPsay.decouverteAndroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MonView extends View {
    public MonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
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
        canvas.drawText(texte, getWidth() / 2, getHeight() / 4, p);
        /* charger l'image emoji.png depuis le dossier drawable */
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.formulaone);
        if (b != null) {
            canvas.drawBitmap(b, getWidth() / 2 - b.getWidth() / 2, getHeight() / 2 - b.getHeight() / 2, p);
        }
    }
}
