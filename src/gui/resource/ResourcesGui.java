package gui.resource;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class ResourcesGui {

	public static final class COLOR {
		
		private static final Color SECOND_COLOR = Color.white;
		private static final Color PRIMARY_COLOR = new Color(31, 97, 141);
		private static final Color ACENT_COLOR = Color.gray;
		private static final Color WARING_COLOR = new Color(235, 49, 49);
		
		
		public static Color getSecondColor() {
			return SECOND_COLOR;
		}
		public static Color getPrimaryColor() {
			return PRIMARY_COLOR;
		}
		public static Color getAcentColor() {
			return ACENT_COLOR;
		}
		
		public static Color getWaringColor() {
			return WARING_COLOR;
		}
	}
	
	public static final class BORDER {
		
		
		private static final Border BORDER_CONTEINER_MAIN  = BorderFactory.createEmptyBorder(15, 15,15,15);
		private static final Border BORDER_FORM = BorderFactory.createEmptyBorder(25,0,25,15);
		private static final Border BORDER_TITLE = BorderFactory.createEmptyBorder(9,9,9,0);
		private static final Border BORDER_TXT = BorderFactory.createMatteBorder(0, 0, 1, 0, ResourcesGui.COLOR.getAcentColor());
		
		private static final Border BORDER_BTN_ACEPT = BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(ResourcesGui.COLOR.getSecondColor(), 2),
	               BorderFactory.createLineBorder(ResourcesGui.COLOR.getPrimaryColor(), 5));
		
		
		private static final Border BORDER_BTN_CANCEL = BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(ResourcesGui.COLOR.getAcentColor(), 2),
	               BorderFactory.createLineBorder(ResourcesGui.COLOR.getSecondColor(), 5));


		public static Border getBorderBtnAcept() {
			return BORDER_BTN_ACEPT;
		}


		public static Border getBorderBtnCancel() {
			return BORDER_BTN_CANCEL;
		}


		public static Border getBorderTxt() {
			return BORDER_TXT;
		}


		public static Border getBorderConteinerMain() {
			return BORDER_CONTEINER_MAIN;
		}


		public static Border getBorderTitle() {
			return BORDER_TITLE;
		}


		public static Border getBorderForm() {
			return BORDER_FORM;
		}
		
	}
	
	public static final class FONT {
		
		private static final Font FONT_TEXT = new Font("Segoe UI", 0, 15);
		private static final Font FONT_TITLE = new Font("Segoe UI", 0, 19);

		public static Font getFontText() {
			return FONT_TEXT;
		} 

		public static Font geFontTitle() {
			return FONT_TITLE;
		}	
	}
	
	public static final class DIMENS {
		private static final int DISTANCE_COMPONENTS = 10;
		
		public static int getDistanteComponent() {
			return DISTANCE_COMPONENTS;
		}
	}
}
