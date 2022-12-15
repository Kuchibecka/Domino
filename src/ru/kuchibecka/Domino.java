package ru.kuchibecka;

import java.util.Objects;

public class Domino {
    private int position;
    private int left;
    private int right;
    private boolean swapped;
    private boolean used;

    public Domino(int position, int left, int right) {
        this.position = position;
        this.left = left;
        this.right = right;
        this.swapped = false;
        this.used = false;
    }

    public void swap() {
        int leftPrev = this.left;
        this.left = this.right;
        this.right = leftPrev;
        this.swapped = !this.swapped;
    }

    @Override
    public String toString() {
        return "Domino{" +
                "position=" + position +
                ", left=" + left +
                ", right=" + right +
                ", swapped=" + swapped +
                ", used=" + used +
                '}';
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Domino domino = (Domino) o;
        return (left == domino.left && right == domino.right) || (left == domino.right && right == domino.left);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, used);
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isUsed() {
        return used;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public boolean isSwapped() {
        return swapped;
    }
}
