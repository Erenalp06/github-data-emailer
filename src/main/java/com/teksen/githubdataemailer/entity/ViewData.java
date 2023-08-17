package com.teksen.githubdataemailer.entity;

import java.util.List;

public record ViewData(int count, int uniques, List<View> views) {
}
