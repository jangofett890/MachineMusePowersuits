package net.machinemuse.powersuits.item;

import java.text.DecimalFormat;
import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;

public class TinkerEffectAdditive extends TinkerEffect {
	public static Random random = new Random();
	public String propertyAffected;
	public double minimumEffect;
	public double maximumEffect;

	public TinkerEffectAdditive(String propertyAffected, double minimumEffect,
			double maximumEffect) {
		super();
		this.propertyAffected = propertyAffected;
		this.minimumEffect = minimumEffect;
		this.maximumEffect = maximumEffect;
	}

	@Override
	public void applyEffect(NBTTagCompound properties) {
		double prev = ItemUtils.getDoubleOrZero(properties, propertyAffected);
		double effect = (maximumEffect - minimumEffect) * random.nextDouble()
				+ minimumEffect;
		if (prev + effect <= 0) {
			properties.removeTag(propertyAffected);
		} else {
			properties.setDouble(propertyAffected, prev + effect);
		}
	}

	@Override
	public String toString() {
		DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
		format.setPositivePrefix("+");
		format.setMaximumFractionDigits(2);
		return this.propertyAffected + " " + format.format(minimumEffect)
				+ "~" + format.format(maximumEffect);
	}

	@Override
	public double simEffectMin(NBTTagCompound properties) {
		return ItemUtils.getDoubleOrZero(properties, propertyAffected)
				+ minimumEffect;
	}

	@Override
	public double simEffectMax(NBTTagCompound properties) {
		return ItemUtils.getDoubleOrZero(properties, propertyAffected)
				+ maximumEffect;
	}
}
